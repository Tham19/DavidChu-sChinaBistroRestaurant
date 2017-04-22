package com.github.thamvannguyen.security;

import com.github.thamvannguyen.core.util.log.LogUtil;
import org.apache.ws.security.*;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.message.*;
import org.apache.wss4j.common.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by huynhduychuong on 10/5/2016.
 */
public class WSSSecurityHandler implements SOAPHandler<SOAPMessageContext>, CallbackHandler {
    private static Properties prop;
    private static Crypto crypto;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            handleRequest(context);
        } else {
            handleResponse(context);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        try {
            LogUtil.debug(XMLUtils.PrettyDocumentToString(toDocument(context.getMessage())));
            Map<String, List<String>> map = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                LogUtil.debug(key + " : " + map.get(key));
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
        return false;
    }


    @Override
    public void close(MessageContext context) {

    }

    private void handleRequest(SOAPMessageContext context) {
        try {
            LogUtil.debug("############################");
            LogUtil.debug("Request plain:");
            LogUtil.debug("############################");
            LogUtil.debug(XMLUtils.PrettyDocumentToString(toDocument(context.getMessage())));
            addTimeStamp(context);
            addUserNameToken(context);
            addSignature(context);
            LogUtil.debug("############################");
            LogUtil.debug("Request full:");
            LogUtil.debug("############################");
            addEncryption(context);
            LogUtil.debug(XMLUtils.PrettyDocumentToString(toDocument(context.getMessage())));
            LogUtil.debug("");
            LogUtil.debug("");
            LogUtil.debug("sending ...");
            LogUtil.debug("");
            LogUtil.debug("");
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
    }

    private void checkSignatureAndDecode(SOAPMessage msg, CallbackHandler cb) throws WSSecurityException, TransformerConfigurationException, TransformerException, SOAPException, IOException,
            Exception {

        WSSecurityEngine secEngine = new WSSecurityEngine();

        Document doc = toDocument(msg);

        List<WSSecurityEngineResult> v = secEngine.processSecurityHeader(doc, null, cb, crypto);

        if (v == null) {
            throw new Exception("Access not granted.");
        }
        // put the decoded message into the object
        updateSOAPMessage(doc, msg);
    }

    private void handleResponse(SOAPMessageContext context) {
        try {
            LogUtil.debug("############################");
            LogUtil.debug("Response encrypted:");
            LogUtil.debug("############################");
            LogUtil.debug(getStringFromDocument(toDocument(context.getMessage())));
            checkSignatureAndDecode(context.getMessage(), this);
            LogUtil.debug("############################");
            LogUtil.debug("Response decrypted:");
            LogUtil.debug("############################");
            LogUtil.debug(getStringFromDocument(toDocument(context.getMessage())));
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
    }

    private String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            LogUtil.error(ex.getMessage(), ex);
            return null;
        }
    }

    private SOAPMessage updateSOAPMessage(Document doc, SOAPMessage message) throws Exception {
        DOMSource domSource = new DOMSource(doc);
        message.getSOAPPart().setContent(domSource);
        return message;
    }

    private void addTimeStamp(final SOAPMessageContext context) throws Exception {
        SOAPMessage message = context.getMessage();
        Document doc = toDocument(message);
        WSSecHeader secHeader = new WSSecHeader();
        secHeader.insertSecurityHeader(doc);
        WSSecTimestamp timestamp = new WSSecTimestamp();
        timestamp.setTimeToLive(300);
        Document created = timestamp.build(doc, secHeader);
        updateSOAPMessage(created, message);
    }

    private void addUserNameToken(final SOAPMessageContext context) throws Exception {
        SOAPMessage message = context.getMessage();
        Document doc = toDocument(message);
        WSSecHeader secHeader = new WSSecHeader();
        secHeader.insertSecurityHeader(doc);
        WSSecUsernameToken sut = new WSSecUsernameToken();
        sut.setUserInfo("client-user", "userpassword");
        Document created = sut.build(doc, secHeader);
        updateSOAPMessage(created, message);
    }

    private void addSignature(SOAPMessageContext context) throws Exception {
        SOAPMessage message = context.getMessage();
        Document doc = toDocument(message);
        WSSecHeader secHeader = new WSSecHeader();
        secHeader.insertSecurityHeader(doc);
        WSSecSignature signature = new WSSecSignature();
        signature.setUserInfo("keyalias", "keypassphrase");
        signature.setKeyIdentifierType(WSConstants.BST_DIRECT_REFERENCE);
        signature.setUseSingleCertificate(true);
        signature.setSignatureAlgorithm("SignatureAlgorithm");
        signature.setDigestAlgo("DigestAlgo");
        signature.setSigCanonicalization("SigCanonicalization");
        List<WSEncryptionPart> parts = new ArrayList<>();
        WSEncryptionPart timestampPart = new WSEncryptionPart(WSConstants.TIMESTAMP_TOKEN_LN, WSConstants.WSU_NS, "");
        WSEncryptionPart bodyPart = new WSEncryptionPart(WSConstants.ELEM_BODY, WSConstants.URI_SOAP11_ENV, "");
        WSEncryptionPart usernameTokenPart = new WSEncryptionPart(WSConstants.USERNAME_TOKEN_LN, WSConstants.WSSE_NS, "");
        parts.add(timestampPart);
        parts.add(bodyPart);
        parts.add(usernameTokenPart);
        signature.setParts(parts);
        Document created = signature.build(doc, crypto, secHeader);
        updateSOAPMessage(created, message);
    }

    private void addEncryption(SOAPMessageContext context) throws Exception {
        SOAPMessage message = context.getMessage();
        Document doc = toDocument(message);
        WSSecHeader secHeader = new WSSecHeader();
        secHeader.insertSecurityHeader(doc);
        Node bodyContent = null;
        if (message.getSOAPBody() != null) {
            bodyContent = message.getSOAPBody().getFirstChild();
        }
        if (bodyContent != null) {
            String namespace = bodyContent.getNamespaceURI();
            String localName = bodyContent.getLocalName();
            WSSecEncrypt encryption = new WSSecEncrypt();
            encryption.setUserInfo("keyAlias", "keyPassword");
            List<WSEncryptionPart> parts = new ArrayList<WSEncryptionPart>();
            WSEncryptionPart bodyPart = new WSEncryptionPart(localName, namespace, "");
            parts.add(bodyPart);
            encryption.setParts(parts);
            Document created = encryption.build(doc, crypto, secHeader);
            updateSOAPMessage(created, message);
        }
    }

    private Document toDocument(SOAPMessage soapMsg) throws SOAPException, TransformerException {
        Source src = soapMsg.getSOAPPart().getContent();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMResult result = new DOMResult();
        transformer.transform(src, result);
        return (Document) result.getNode();
    }
}

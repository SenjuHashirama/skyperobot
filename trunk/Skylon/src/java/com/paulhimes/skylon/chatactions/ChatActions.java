package com.paulhimes.skylon.chatactions;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.paulhimes.skylon.XmlParseException;

public class ChatActions {

	private List<ChatAction> actions;

	public ChatActions(List<ChatAction> actions) {
		this.actions = actions;
	}

	public ChatActions() {
		this(new ArrayList<ChatAction>());
	}

	public List<ChatAction> getActions() {
		return actions;
	}

	public static ChatActions parseXml(Element chatActionsNode) throws XmlParseException {
		if (chatActionsNode.getNodeName().equalsIgnoreCase("chatActions")) {

			List<ChatAction> actions = new ArrayList<ChatAction>();

			NodeList childNodes = chatActionsNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);

				try {
					if (childNode.getNodeName().equalsIgnoreCase("simpleReplyChatAction")) {
						actions.add(SimpleReplyChatAction.parseXml((Element) childNode));
					}
				} catch (XmlParseException xpe) {
					xpe.printStackTrace();
				}
			}

			return new ChatActions(actions);
		}

		throw new XmlParseException("chatActions");
	}
}
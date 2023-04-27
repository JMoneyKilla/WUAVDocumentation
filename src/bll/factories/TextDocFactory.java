package bll.factories;

import be.documents.IDocument;
import be.documents.TextDoc;
import bll.DocumentFactory;

public class TextDocFactory extends DocumentFactory {
    @Override
    public IDocument createDocument() {
        return new TextDoc();
    }
}

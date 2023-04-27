package bll.factories;

import be.documents.DiagramDoc;
import be.documents.IDocument;
import bll.DocumentFactory;

public class DiagramDocFactory extends DocumentFactory {
    @Override
    public IDocument createDocument() {
        return new DiagramDoc();
    }
}

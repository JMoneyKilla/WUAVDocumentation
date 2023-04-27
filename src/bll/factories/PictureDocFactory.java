package bll.factories;

import be.documents.IDocument;
import be.documents.PictureDoc;
import bll.DocumentFactory;

public class PictureDocFactory extends DocumentFactory {
    @Override
    public IDocument createDocument() {
        return new PictureDoc();
    }
}

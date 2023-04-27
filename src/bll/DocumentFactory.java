package bll;

import be.documents.IDocument;

public abstract class DocumentFactory {

    public IDocument makeDocument(){
        return createDocument();
    }

    public abstract IDocument createDocument();
}

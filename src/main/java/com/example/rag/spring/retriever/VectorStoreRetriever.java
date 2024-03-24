package com.example.rag.spring.retriever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.List;
import java.util.Objects;

public class VectorStoreRetriever implements DocumentRetriever {

	private static final Logger logger = LoggerFactory.getLogger(VectorStoreRetriever.class);

	private final VectorStore vectorStore;

	private final SearchRequest searchRequest;

	public VectorStoreRetriever(VectorStore vectorStore) {
		this(vectorStore, SearchRequest.defaults());
	}

	public VectorStoreRetriever(VectorStore vectorStore, SearchRequest searchRequest) {
		Objects.requireNonNull(vectorStore, "VectorStore should not be null");
		Objects.requireNonNull(searchRequest, "SearchRequest should not be null");
		this.vectorStore = vectorStore;
		this.searchRequest = searchRequest;
	}

	@Override
	public List<Document> retrieve(String message) {
		logger.info("Retrieving relevant documents");
		List<Document> similarDocuments = vectorStore.similaritySearch(message);
		logger.info("Found {} relevant documents.", similarDocuments.size());
		return similarDocuments;
	}

}
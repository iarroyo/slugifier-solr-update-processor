package org.apache.solr.scg.handler.dataimport.updateprocessors;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.update.AddUpdateCommand;
import org.apache.solr.update.processor.UpdateRequestProcessor;

/**
 * 
 * @author iarroyo
 *
 */
public class SlugUpdateProcessor extends UpdateRequestProcessor {

	private String source_slug;
	private String target_slug;

	public SlugUpdateProcessor(String source_slug, String target_slug, UpdateRequestProcessor next) {
		super(next);
		this.source_slug = source_slug;
		this.target_slug = target_slug;
	}

	@Override
	public void processAdd(AddUpdateCommand cmd) throws IOException {

		SolrInputDocument doc = cmd.getSolrInputDocument();

		if (doc.containsKey(this.source_slug)) {
			String auxSlug = (String) doc.getFieldValue(this.source_slug);
			// TODO transliterate
			auxSlug = auxSlug.replaceAll("[^a-zA-Z0-9=\\s—–-]+", "");
			auxSlug = auxSlug.replaceAll("[=\\s—–-]+", "-");
			auxSlug = auxSlug.trim().toLowerCase();

			if (doc.containsKey(this.target_slug)) {
				doc.setField(this.target_slug, auxSlug);
			} else {
				doc.addField(this.target_slug, auxSlug);
			}

		}

		super.processAdd(cmd);
	}

}

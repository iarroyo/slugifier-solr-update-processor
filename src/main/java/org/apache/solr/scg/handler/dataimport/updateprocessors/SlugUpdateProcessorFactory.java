package org.apache.solr.scg.handler.dataimport.updateprocessors;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.update.processor.UpdateRequestProcessor;
import org.apache.solr.update.processor.UpdateRequestProcessorFactory;

/**
 * 
 * @author iarroyo
 *
 */
public class SlugUpdateProcessorFactory extends UpdateRequestProcessorFactory {

	private String source_slug;
	private String target_slug;

	@Override
	public void init(final NamedList args) {
		if (args != null) {
			SolrParams params = SolrParams.toSolrParams(args);
			source_slug = params.get("source_slug");
			target_slug = params.get("target_slug");
		}
	}

	@Override
	public UpdateRequestProcessor getInstance(SolrQueryRequest req, SolrQueryResponse rsp,
			UpdateRequestProcessor next) {
		return new SlugUpdateProcessor(source_slug, target_slug, next);
	}
	
}

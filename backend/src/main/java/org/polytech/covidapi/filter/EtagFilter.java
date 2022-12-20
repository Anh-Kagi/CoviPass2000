package org.polytech.covidapi.filter;

import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EtagFilter extends ShallowEtagHeaderFilter {
	private final List<String> modMethods = Arrays.asList(HttpMethod.POST.toString(), HttpMethod.PUT.toString(), HttpMethod.DELETE.toString());
	private final Set<String> cached = new HashSet<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain chain) throws ServletException, IOException {
		// si method alternative, check etag, error si different
		if (modMethods.contains(request.getMethod()) && !cached.contains(request.getHeader(HttpHeaders.IF_MATCH))) {
			response.sendError(HttpStatus.PRECONDITION_FAILED.value());
			return;
		}

		// sinon on s'en remet à parent
		super.doFilterInternal(request, response, chain);

		// et on update le cache
		String ifMatch = request.getHeader(HttpHeaders.IF_MATCH);
		if (Strings.isNotBlank(ifMatch)) {
			cached.remove(ifMatch);
		}

		String etag = response.getHeader(HttpHeaders.ETAG);
		if (Strings.isNotBlank(etag)) {
			cached.add(etag);
		}
	}

	@Override
	protected boolean isEligibleForEtag(@NonNull HttpServletRequest request,
										@NonNull HttpServletResponse response,
										int responseStatusCode,
										@NonNull InputStream inputStream) {
		if (responseStatusCode >= 200 && responseStatusCode < 300) {
			String cacheControl = response.getHeader(HttpHeaders.CACHE_CONTROL);
			return cacheControl == null || !cacheControl.contains("no-store");
		}
		return false;
	}
}
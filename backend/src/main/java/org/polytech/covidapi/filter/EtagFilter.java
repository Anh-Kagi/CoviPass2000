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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtagFilter extends ShallowEtagHeaderFilter {
	private final List<String> statMethods = Arrays.asList(HttpMethod.GET.toString(), HttpMethod.PUT.toString());
	private final List<String> modMethods = Arrays.asList(/*HttpMethod.POST.toString(), */HttpMethod.PUT.toString(), HttpMethod.DELETE.toString());

	private final Map<String, String> etags = new HashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain chain) throws ServletException, IOException {
		// get path
		String path = request.getRequestURI();

		// si get, check if-none-match
		if (HttpMethod.GET.toString().equals(request.getMethod())) {
			String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);
			if (ifNoneMatch != null && ifNoneMatch.equals(etags.get(path))) {
				response.setStatus(HttpStatus.NOT_MODIFIED.value());
				return;
			}
		}

		// si method alternative, check etag, error si different
		if (modMethods.contains(request.getMethod()) && !request.getHeader(HttpHeaders.IF_MATCH).equals(etags.get(path))) {
			response.sendError(HttpStatus.PRECONDITION_FAILED.value());
			return;
		}

		// sinon on s'en remet à parent
		super.doFilterInternal(request, response, chain);

		// et on update le cache
		String etag = response.getHeader(HttpHeaders.ETAG);
		if (Strings.isNotBlank(etag)) {
			etags.put(path, etag); // new tag (GET/PUT)
		} else {
			etags.remove(path); // no tag (DELETE)
		}
	}

	@Override
	protected boolean isEligibleForEtag(@NonNull HttpServletRequest request,
										@NonNull HttpServletResponse response,
										int responseStatusCode,
										@NonNull InputStream inputStream) {
		// generate an Etag if method shows data and is successful
		return responseStatusCode >= 200 && responseStatusCode < 300 && statMethods.contains(request.getMethod());
	}
}
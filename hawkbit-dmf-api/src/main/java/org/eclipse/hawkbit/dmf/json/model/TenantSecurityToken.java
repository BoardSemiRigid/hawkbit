/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.dmf.json.model;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON representation to authenticate a tenant.
 */

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantSecurityToken {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String COAP_AUTHORIZATION_HEADER = "Coap-Authorization";
    public static final String COAP_TOKEN_VALUE = "CoapToken";

    @JsonProperty
    private final String tenant;
    @JsonProperty
    private final String controllerId;
    @JsonProperty(required = false)
    private Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @JsonProperty(required = false)
    private final FileResource fileResource;

    /**
     * Constructor.
     * 
     * @param tenant
     *            the tenant for the security token
     * @param controllerId
     *            the ID of the controller for the security token
     * @param fileResource
     *            the file to obtain
     */
    @JsonCreator
    public TenantSecurityToken(@JsonProperty("tenant") final String tenant,
            @JsonProperty("controllerId") final String controllerId,
            @JsonProperty("fileResource") final FileResource fileResource) {
        this.tenant = tenant;
        this.controllerId = controllerId;
        this.fileResource = fileResource;
    }

    public String getTenant() {
        return tenant;
    }

    public String getControllerId() {
        return controllerId;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public FileResource getFileResource() {
        return fileResource;
    }

    /**
     * Gets a header value.
     * 
     * @param name
     *            of header
     * @return the value
     */
    public String getHeader(final String name) {
        return headers.get(name);
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.headers.putAll(headers);
    }

    /**
     * File resource descriptor which is used to ask for the resource to
     * download e.g. The lookup of the file can be different e.g. by SHA1 hash
     * or by filename.
     */
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FileResource {
        @JsonProperty(required = false)
        private String sha1;
        @JsonProperty(required = false)
        private String filename;
        @JsonProperty(required = false)
        private Long artifactId;

        public String getSha1() {
            return sha1;
        }

        public void setSha1(final String sha1) {
            this.sha1 = sha1;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(final String filename) {
            this.filename = filename;
        }

        public Long getArtifactId() {
            return artifactId;
        }

        public void setArtifactId(final Long artifactId) {
            this.artifactId = artifactId;
        }

        /**
         * factory method to create a file resource for an SHA1 lookup.
         * 
         * @param sha1
         *            the SHA1 key of the file to obtain
         * @return the {@link FileResource} with SHA1 key set
         */
        public static FileResource sha1(final String sha1) {
            final FileResource resource = new FileResource();
            resource.sha1 = sha1;
            return resource;
        }

        /**
         * factory method to create a file resource for an filename lookup.
         * 
         * @param filename
         *            the filename of the file to obtain
         * @return the {@link FileResource} with filename set
         */
        public static FileResource filename(final String filename) {
            final FileResource resource = new FileResource();
            resource.filename = filename;
            return resource;
        }

        /**
         * factory method to create a file resource for an artifactId lookup.
         * 
         * @param artifactId
         *            the artifactId of the file to obtain
         * @return the {@link FileResource} with artifactId set
         */
        public static FileResource artifactId(final Long artifactId) {
            final FileResource resource = new FileResource();
            resource.artifactId = artifactId;
            return resource;
        }

        @Override
        public String toString() {
            return "FileResource [sha1=" + sha1 + ", filename=" + filename + "]";
        }
    }
}

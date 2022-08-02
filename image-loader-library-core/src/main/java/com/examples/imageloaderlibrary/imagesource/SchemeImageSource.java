package com.examples.imageloaderlibrary.imagesource;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;

import com.examples.imageloaderlibrary.util.ResourceIdentifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static android.R.attr.scheme;

/**
 * Created by Mariusz
 * <p>
 * Experimental
 */
public class SchemeImageSource implements ImageSource {
    private final String uri;
    private final Object extra;

    public SchemeImageSource(String uri, Object extra) {
        this.uri = uri;
        this.extra = extra;
    }

    @Override
    public InputStream getStream() throws IOException {
        Scheme scheme = Scheme.ofUri(uri);
        switch (scheme) {
            case FILE:
                return new FileImageSource(scheme.unwrap(uri)).getStream();
            case ASSET:
                return new AssetImageSource((AssetManager) extra, scheme.unwrap(uri)).getStream();
            case RESOURCE:
                ResourceIdentifier resourceIdentifier = new ResourceIdentifier(scheme.unwrap(uri));
                return new ResourceImageSource((Context) extra, resourceIdentifier.getIdentifier((Resources) extra)).getStream();
            case CONTENT_RESOLVER:
                return new ContentResolverImageSource((ContentResolver) extra, Uri.parse(scheme.unwrap(uri))).getStream();
            case UNKNOWN:
            default:
                return new UnknownImageSource().getStream();
        }
    }

    @Override
    public String toString() {
        return "SchemeImageSource{" +
                "uri='" + uri + '\'' +
                '}';
    }

    public enum Scheme {
        FILE("file://"),
        ASSET("asset://"),
        RESOURCE("resource://"),
        CONTENT_RESOLVER("content-resolver://"),
        UNKNOWN("");

        String uriPrefix;

        Scheme(String uriPrefix) {
            this.uriPrefix = uriPrefix;
        }

        public String unwrap(String uri) {
            if (!belongsTo(uri)) {
                throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", uri, scheme));
            }
            return uri.substring(uriPrefix.length());
        }

        public String wrap(String path) {
            return uriPrefix + path;
        }

        public static Scheme ofUri(String uri) {
            for (Scheme scheme : values()) {
                if (uri.startsWith(scheme.uriPrefix))
                    return scheme;
            }
            return UNKNOWN;
        }

        private boolean belongsTo(String uri) {
            return uri.toLowerCase(Locale.US).startsWith(uriPrefix);
        }
    }
}

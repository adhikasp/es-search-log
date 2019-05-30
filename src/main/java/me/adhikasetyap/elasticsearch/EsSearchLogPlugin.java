package me.adhikasetyap.elasticsearch;

import org.elasticsearch.common.util.concurrent.ThreadContext;
import org.elasticsearch.http.HttpRequest;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.rest.RestRequest;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class EsSearchLogPlugin extends Plugin implements ActionPlugin {

    @Override
    public UnaryOperator<RestHandler> getRestHandlerWrapper(ThreadContext threadContext) {
        return originalHandler -> (RestHandler) (request, channel, client) -> {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/logs/es-search.log", true);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                RestRequest x = request;
                HttpRequest r = request.getHttpRequest();
                printWriter.printf("Inbound for URI %s %s with header %s and content %s\n",
                        r.method(), r.uri(), Arrays.toString(r.getHeaders().entrySet().toArray()), r.content().utf8ToString());
            } finally {
                originalHandler.handleRequest(request, channel, client);
            }
        };
    }

}
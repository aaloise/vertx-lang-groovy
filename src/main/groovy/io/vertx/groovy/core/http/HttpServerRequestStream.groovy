/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.core.http;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.groovy.core.streams.ReadStream
import io.vertx.core.Handler
/**
 * A {@link io.vertx.core.streams.ReadStream} of {@link io.vertx.core.http.HttpServerRequest}, used for
 * notifying http request to a {@link io.vertx.core.http.HttpServer}.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@CompileStatic
public class HttpServerRequestStream implements ReadStream<HttpServerRequest> {
  final def io.vertx.core.http.HttpServerRequestStream delegate;
  public HttpServerRequestStream(io.vertx.core.http.HttpServerRequestStream delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public HttpServerRequestStream exceptionHandler(Handler<Throwable> handler) {
    this.delegate.exceptionHandler(handler);
    return this;
  }
  public HttpServerRequestStream handler(Handler<HttpServerRequest> handler) {
    this.delegate.handler(new Handler<io.vertx.core.http.HttpServerRequest>() {
      public void handle(io.vertx.core.http.HttpServerRequest event) {
        handler.handle(HttpServerRequest.FACTORY.apply(event));
      }
    });
    return this;
  }
  public HttpServerRequestStream pause() {
    this.delegate.pause();
    return this;
  }
  public HttpServerRequestStream resume() {
    this.delegate.resume();
    return this;
  }
  public HttpServerRequestStream endHandler(Handler<Void> endHandler) {
    this.delegate.endHandler(endHandler);
    return this;
  }

  static final java.util.function.Function<io.vertx.core.http.HttpServerRequestStream, HttpServerRequestStream> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.core.http.HttpServerRequestStream arg -> new HttpServerRequestStream(arg);
  };
}

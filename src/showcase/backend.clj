(ns showcase.backend
  (:require
   [cheshire.core :as core]
   [cheshire.core :as json]
   [clojure.pprint :as pprint]
   [hiccup2.core :as h]
   [muuntaja.core :as m]
   [reitit.coercion.malli]
   [reitit.dev.pretty :as pretty]
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.malli]
   [reitit.ring.middleware.parameters :as parameters]
   [ring.adapter.jetty :as adapter]
   [showcase.highcharts.plots :as plots]
   [showcase.vega-lite.vega-plots :as vega-lite]
   [showcase.views.index :as index]
   showcase.views.jupyter))

(def app
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler (fn [_] {:status 200
                                   :body (index/index-page)})}}]
     ["/api"]
     ["/public/*" (ring/create-resource-handler)]]

    {:data {:exception pretty/exception
            :coercion reitit.coercion.malli/coercion
            :muuntaja m/instance
            #_:conflicts #_(constantly nil)
            :middleware [parameters/parameters-middleware
                         coercion/coerce-request-middleware]}})
   (ring/routes (ring/create-default-handler))))

(comment
  (def server
    (adapter/run-jetty #'app {:port 8090
                              :join? false}))
  (.stop server))

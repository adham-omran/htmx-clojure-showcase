(ns showcase.views.jupyter
  (:require
   [cheshire.core :as core]
   [hiccup.page :as page]
   [hiccup2.core :as h]
   [showcase.highcharts.plots :as plots]))

(defn index-page
  []
  (page/html5 {:ng-app "myApp" :lang "en"}
              [:head
               [:meta {:charset "UTF-8"}]
               [:title "input"]
               [:link {:rel "icon"
                       :href "data:;base64,iVBORw0KGgo="}]

               [:body
                (page/include-js "./public/htmx.min.js")
                (page/include-js "https://cdn.jsdelivr.net/npm/vega@5")
                (page/include-js "https://cdn.jsdelivr.net/npm/vega-lite@5")
                (page/include-js "https://cdn.jsdelivr.net/npm/vega-embed@6")
                [:div
                 [:div#vega]
                 [:div
                  [:input {:type "range"
                           :id "v"
                           :name "v"
                           :min "0"
                           :max "11"
                           :hx-post "/api/slider-demo"
                           :hx-trigger "change"
                           :hx-swap "innerhtml"
                           :hx-target "next"}
                   "Input"]
                  [:div]]
                 [:div
                  [:textarea
                   {:name "q"
                    :hx-post "/api/input"
                    :hx-trigger "keyup changed"
                    :hx-target "next"
                    :hx-swap "innerHTML"}]
                  [:div]]
                 [:div
                  [:textarea
                   {:name "q"
                    :hx-post "/api/input"
                    :hx-trigger "keyup changed"
                    :hx-target "next"
                    :hx-swap "innerHTML"}]
                  [:div]]
                 [:div#next]]
                [:button
                 {:hx-get "/api/next-cell"
                  :hx-target "#next"
                  :hx-swap "outerHTML"}
                 "Add cell."]]]))

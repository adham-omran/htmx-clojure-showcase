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
                [:div
                 [:textarea
                  {:name "q"
                   :hx-post "/api/input"
                   :hx-trigger "keyup changed"
                   :hx-target "#here"
                   :hx-swap "innerHTML"}]
                 [:div#here]]
                [:div
                 [:textarea
                  {:name "q"
                   :hx-post "/api/input"
                   :hx-trigger "keyup changed"
                   :hx-target "next"
                   :hx-swap "innerHTML"}]
                 [:div]]]]))

(ns showcase.views.index
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
               [:title "app"]
               [:link {:rel "icon"
                       :href "data:;base64,iVBORw0KGgo="}]
               (page/include-css "./public/css/output.css") ;; tailwind output
               (page/include-js "./public/htmx.min.js") ;; lib
               (page/include-js "./public/js/highcharts.js")
               (page/include-js "./public/js/modules/histogram-bellcurve.js")
               (page/include-js "./public/js/modules/accessibility.js")
               (page/include-js "./public/js/vega5.js")
               (page/include-js "./public/js/vega-lite5.js")
               (page/include-js "./public/js/vega-embed6.js")
               ;; Plotly
               #_(page/include-js "./public/js/d3.js")
               [:body
                [:h1
                 {:class "text-4xl flex w-full justify-center items-center p-4 mb-4 bg-slate-200"}
                 "Showcase"]
                [:div
                 {:class "grid grid-flow-col auto-cols-auto gap-x-10"}
                 [:button
                  {:class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                   :hx-post "/api/vega-lite"
                   :hx-target "#showcase"}
                  "vega-lite"]
                 [:button
                  {:class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                   :hx-post "/api/highcharts"
                   :hx-target "#showcase"}
                  "highcharts"]
                 [:button
                  {:class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
                   :hx-post "/api/plotly"
                   :hx-target "#showcase"}
                  "plotly"]]
                [:div#showcase]]]))

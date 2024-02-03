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
               (page/include-js "https://code.highcharts.com/modules/histogram-bellcurve.js")
               (page/include-js "https://cdn.jsdelivr.net/npm/vega@5")
               (page/include-js "https://cdn.jsdelivr.net/npm/vega-lite@5")
               (page/include-js "https://cdn.jsdelivr.net/npm/vega-embed@6")
               [:body
                [:h1
                 {:class "text-4xl flex w-full justify-center items-center p-4 mb-4 bg-slate-200"}
                 "Showcase"]
                [:div
                 {:class "grid grid-cols-2"}
                 [:button
                  "vega-lite"]
                 [:button
                  {:class "bg-sky-300"}
                  "highcharts"]]
                #_[:div
                   [:script
                    (str (h/raw
                          "vegaEmbed(document.currentScript.parentElement, "
                          (core/generate-string
                           {"data" {"values" [{"a" "A", "b" 39}]},
                            "mark" "bar",
                            "encoding"
                            {"x" {"field" "a", "type" "nominal", "axis" {"labelAngle" 0}},
                             "y" {"field" "b", "type" "quantitative"}}})
                          ").catch(console.error);"))]]
                [:div
                 {:class "grid"}
                 [:div#graph]
                 [:div#showcase
                  [:div
                   [:script
                    (str (h/raw (format "Highcharts.chart(document.currentScript.parentElement, %s)"
                                        (plots/hc-bar-chart))))]]
                  [:div
                   [:script
                    (str (h/raw (format "Highcharts.chart(document.currentScript.parentElement, %s)"
                                        (plots/hc-historgram-chart))))]]]]]]))

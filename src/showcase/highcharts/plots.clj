(ns showcase.highcharts.plots
  (:require
   [cheshire.core :as json]))

(defn hc-bar-chart
  []
  (json/generate-string
   {:xAxis [{:index 0
             :isX true
             :title {}
             :labels {}}]
    :subtitle {:text ""}

    :title {:text "Test"}
    :plotOptions {:series {:states {:inactive {}}}}
    :series
    [{:data
      (into [] (for [i (range 1 4)
                     p [(rand-int 10)]]
                 [i p]))
      :type :bar}]}))


(defn hc-historgram-chart
  []
  (json/generate-string
   {:xAxis [{:index 0
             :isX true
             :title {}
             :labels {}}]
    :subtitle {:text ""}

    :title {:text "Test"}
    :plotOptions {:series {:states {:inactive {}}}}
    :series
    [{:data (take 20 (repeatedly #(rand-int 10)))
      :baseSeries 1
      :type :histogram}]}))

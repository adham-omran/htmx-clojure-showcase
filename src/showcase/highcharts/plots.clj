(ns showcase.highcharts.plots
  (:require
   [cheshire.core :as json]
   [clojure.java.shell :as sh]
   [showcase.highcharts.data :as data]))

(def colors ["#2caffe", "#544fc5", "#00e272", "#fe6a35", "#6b8abc", "#d568fb", "#2ee0ca", "#fa4b42", "#feb56a", "#91e8e1"])

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

(defn line-chart
  []
  (json/generate-string
   {:title {:text "Line chart"}
    :subtitle {:text "By Job Category"}
    :yAxis {:title {:text "Number of Employees"}}
    :series [{:name "Installation & Developers"
              :data [43934, 48656, 65165, 81827, 112143, 142383,
                     171533, 165174, 155157, 161454, 154610]}

             {:name "Manufacturing",
              :data [24916, 37941, 29742, 29851, 32490, 30282,
                     38121, 36885, 33726, 34243, 31050]}

             {:name "Sales & Distribution",
              :data [11744, 30000, 16005, 19771, 20185, 24377,
                     32147, 30912, 29243, 29213, 25663]}

             {:name "Operations & Maintenance",
              :data [nil, nil, nil, nil, nil, nil, nil,
                     nil, 11164, 11218, 10077]}

             {:name "Other",
              :data [21908, 5548, 8105, 11248, 8989, 11816, 18274,
                     17300, 13053, 11906, 10073]}]

    :xAxis {:accessibility {:rangeDescription "Range: 2010 to 2020"}}

    :legend {:layout "vertical",
             :align "right",
             :verticalAlign "middle"}

    :plotOptions {:series {:label {:connectorAllowed false},
                           :pointStart 2010}}

    :responsive {:rules [{:condition {:maxWidth 500},
                          :chartOptions {:legend {:layout "horizontal",
                                                  :align "center",
                                                  :verticalAlign "bottom"}}}]}}))

(defn spline-with-symbols
  []
  (json/generate-string
   {:chart {:type "spline"},
    :title {:text "Monthly Average Temperature"},
    :subtitle {:text (str "Source: "
                          "<a href=\"https://en.wikipedia.org/wiki/List_of_cities_by_average_temperature\""
                          "target=\"blank\">Wikipedia.com</a>")},
    :xAxis {:categories ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                         "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            :accessibility {:description "Months of the year"}}

    :yAxis {:title {:text "Temperature"},
            :labels {:format "{value}°"}}

    :tooltip {:crosshairs true,
              :shared true},
    :plotOptions {:spline {:marker {:radius 4,
                                    :lineColor "#666666",
                                    :lineWidth 1}}}

    :series [{:name "Tokyo",
              :marker {:symbol "square"},
              :data [5.2, 5.7, 8.7, 13.9, 18.2, 21.4, 25.0, {:y 26.4,
                                                             :marker {:symbol "url(https://www.highcharts.com/samples/graphics/sun.png)"},
                                                             :accessibility {:description "Sunny symbol, this is the warmest point in the chart."}}, 22.8, 17.5, 12.1, 7.6]}, {:name "Bergen",
                                                                                                                                                                               :marker {:symbol "diamond"},
                                                                                                                                                                               :data [{:y 1.5,
                                                                                                                                                                                       :marker {:symbol "url(https://www.highcharts.com/samples/graphics/snow.png)"},
                                                                                                                                                                                       :accessibility {:description "Snowy symbol, this is the coldest point in the chart."}}, 1.6, 3.3, 5.9, 10.5, 13.5, 14.5, 14.4, 11.5, 8.7, 4.7, 2.6]}]}))

(defn spline-with-inverted-axis
  []
  (json/generate-string
   {:chart {:type "spline",
            :inverted true},
    :title {:text "Atmosphere Temperature by Altitude",
            :align "left"},
    :subtitle {:text "According to the Standard Atmosphere Model",
               :align "left"},
    :xAxis {:reversed false,
            :title {:enabled true,
                    :text "Altitude"},
            :labels {:format "{value} km"},
            :accessibility {:rangeDescription "Range: 0 to 80 km."},
            :maxPadding 0.05,
            :showLastLabel true},
    :yAxis {:title {:text "Temperature"},
            :labels {:format "{value}°"},
            :accessibility {:rangeDescription "Range: -90°C to 20°C."},
            :lineWidth 2},
    :legend {:enabled false},
    :tooltip {:headerFormat "<b>{series.name}</b><br/>",
              :pointFormat "{point.x} km: {point.y}°C"},
    :plotOptions {:spline {:marker {:enable false}}}

    :series [{:name "Temperature",
              :_data [[0, 15], [10, -50], [20, -56.5], [30, -46.5], [40, -22.1],
                      [50, -2.5], [60, -27.7], [70, -55.7], [80, -76.5]],
              :data [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}]}))

(defn spline-with-data-labels
  []
  (json/generate-string

   {:chart {:type "line"},
    :title {:text "Monthly Average Temperature"},
    :subtitle {:text (str "Source: "
                          "<a href=\"https://en.wikipedia.org/wiki/List_of_cities_by_average_temperature\""
                          "target=\"blank\">Wikipedia.com</a>")},
    :xAxis {:categories ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]},
    :yAxis {:title {:text "Temperature (°C)"}}

    :plotOptions {:line {:dataLabels {:enabled true},
                         :enableMouseTracking false}}

    :series [{:name "Reggane",
              :data [16.0, 18.2, 23.1, 27.9, 32.2, 36.4, 39.8, 38.4, 35.5, 29.2,
                     22.0, 17.8]}, {:name "Tallinn",
                                    :data [-2.9, -3.6, -0.6, 4.8, 10.2, 14.5, 17.6, 16.5, 12.0, 6.5,
                                           2.0, -0.9]}]}))

(defn logarithmic-axis
  []
  (json/generate-string
   {:title {:text "Logarithmic axis demo"}

    :xAxis {:tickInterval 1,
            :type "logarithmic",
            :accessibility {:rangeDescription "Range: 1 to 10"}}

    :yAxis {:type "logarithmic",
            :minorTickInterval 0.1,
            :accessibility {:rangeDescription "Range: 0.1 to 1000"}}

    :tooltip {:headerFormat "<b>{series.name}</b><br />",
              :pointFormat "x = {point.x}, y = {point.y}"}

    :series [{:data [1, 2, 4, 8, 16, 32, 64, 128, 256, 512],
              :pointStart 1}]}))

;; TODO: Line chart with custom entrance animation
;;       https://www.highcharts.com/demo/highcharts/line-custom-entrance-animation

;; TODO: Ajax loaded data, clickable points
;;       https://www.highcharts.com/demo/highcharts/line-ajax

(defn line-chart-with-annotations
  []
  (json/generate-string
   {:chart {:type "area",
            :zoomType "x",
            :panning true,
            :panKey "shift",
            :scrollablePlotArea {:minWidth 600}}

    :caption {:text "This chart uses the Highcharts Annotations feature to place labels at various points of interest. The labels are responsive and will be hidden to avoid overlap on small screens."}

    :title {:text "2017 Tour de France Stage 8: Dole - Station des Rousses",
            :align "left"}

    :accessibility {:description "This line chart uses the Highcharts Annotations feature to place labels at various points of interest. The labels are responsive and will be hidden to avoid overlap on small screens. Image description: An annotated line chart illustrates the 8th stage of the 2017 Tour de France cycling race from the start point in Dole to the finish line at Station des Rousses. Altitude is plotted on the Y-axis, and distance is plotted on the X-axis. The line graph is interactive, and the user can trace the altitude level along the stage. The graph is shaded below the data line to visualize the mountainous altitudes encountered on the 187.5-kilometre stage. The three largest climbs are highlighted at Col de la Joux, Côte de Viry and the final 11.7-kilometer, 6.4% gradient climb to Montée de la Combe de Laisia Les Molunes which peaks at 1200 meters above sea level. The stage passes through the villages of Arbois, Montrond, Bonlieu, Chassal and Saint-Claude along the route.",
                    :landmarkVerbosity "one"}

    :lang {:accessibility {:screenReaderSection {:annotations {:descriptionNoPoints "{annotationText}, at distance {annotation.options.point.x}km, elevation {annotation.options.point.y} meters."}}}}

    :credits {:enabled false}

    :annotations [{:draggable "",
                   :labelOptions {:backgroundColor "rgba(255,255,255,0.5)",
                                  :verticalAlign "top",
                                  :y 15},
                   :labels [{:point {:xAxis 0,
                                     :yAxis 0,
                                     :x 27.98,
                                     :y 255},
                             :text "Arbois"}, {:point {:xAxis 0,
                                                       :yAxis 0,
                                                       :x 45.5,
                                                       :y 611},
                                               :text "Montrond"}, {:point {:xAxis 0,
                                                                           :yAxis 0,
                                                                           :x 63,
                                                                           :y 651},
                                                                   :text "Mont-sur-Monnet"}, {:point {:xAxis 0,
                                                                                                      :yAxis 0,
                                                                                                      :x 84,
                                                                                                      :y 789},
                                                                                              :x -10,
                                                                                              :text "Bonlieu"}, {:point {:xAxis 0,
                                                                                                                         :yAxis 0,
                                                                                                                         :x 129.5,
                                                                                                                         :y 382},
                                                                                                                 :text "Chassal"}, {:point {:xAxis 0,
                                                                                                                                            :yAxis 0,
                                                                                                                                            :x 159,
                                                                                                                                            :y 443},
                                                                                                                                    :text "Saint-Claude"}]}, {:draggable "",
                                                                                                                                                              :labels [{:point {:xAxis 0,
                                                                                                                                                                                :yAxis 0,
                                                                                                                                                                                :x 101.44,
                                                                                                                                                                                :y 1026},
                                                                                                                                                                        :x -30,
                                                                                                                                                                        :text "Col de la Joux"}, {:point {:xAxis 0,
                                                                                                                                                                                                          :yAxis 0,
                                                                                                                                                                                                          :x 138.5,
                                                                                                                                                                                                          :y 748},
                                                                                                                                                                                                  :text "Côte de Viry"}, {:point {:xAxis 0,
                                                                                                                                                                                                                                  :yAxis 0,
                                                                                                                                                                                                                                  :x 176.4,
                                                                                                                                                                                                                                  :y 1202},
                                                                                                                                                                                                                          :text "Montée de la Combe <br>de Laisia Les Molunes"}]}, {:draggable "",
                                                                                                                                                                                                                                                                                    :labelOptions {:shape "connector",
                                                                                                                                                                                                                                                                                                   :align "right",
                                                                                                                                                                                                                                                                                                   :justify false,
                                                                                                                                                                                                                                                                                                   :crop true,
                                                                                                                                                                                                                                                                                                   :style {:fontSize "10px",
                                                                                                                                                                                                                                                                                                           :textOutline "1px white"}}

                                                                                                                                                                                                                                                                                    :labels [{:point {:xAxis 0,
                                                                                                                                                                                                                                                                                                      :yAxis 0,
                                                                                                                                                                                                                                                                                                      :x 96.2,
                                                                                                                                                                                                                                                                                                      :y 783},
                                                                                                                                                                                                                                                                                              :text "6.1 km climb <br>4.6% on avg."}, {:point {:xAxis 0,
                                                                                                                                                                                                                                                                                                                                               :yAxis 0,
                                                                                                                                                                                                                                                                                                                                               :x 134.5,
                                                                                                                                                                                                                                                                                                                                               :y 540},
                                                                                                                                                                                                                                                                                                                                       :text "7.6 km climb <br>5.2% on avg."}, {:point {:xAxis 0,
                                                                                                                                                                                                                                                                                                                                                                                        :yAxis 0,
                                                                                                                                                                                                                                                                                                                                                                                        :x 172.2,
                                                                                                                                                                                                                                                                                                                                                                                        :y 925},
                                                                                                                                                                                                                                                                                                                                                                                :text "11.7 km climb <br>6.4% on avg."}]}]

    :xAxis {:labels {:format "{value} km"},
            :minRange 5,
            :title {:text "Distance"},
            :accessibility {:rangeDescription "Range: 0 to 187.8km."}}

    :yAxis {:startOnTick true,
            :endOnTick false,
            :maxPadding 0.35,
            :title {:text nil},
            :labels {:format "{value} m"},
            :accessibility {:description "Elevation",
                            :rangeDescription "Range: 0 to 1,553 meters"}}

    :tooltip {:headerFormat "Distance: {point.x:.1f} km<br>",
              :pointFormat "{point.y} m a. s. l.",
              :shared true}

    :legend {:enabled false}

    :series [{:data data/elevation-data,
              :lineColor (nth ["#2caffe", "#544fc5", "#00e272", "#fe6a35", "#6b8abc", "#d568fb", "#2ee0ca", "#fa4b42", "#feb56a", "#91e8e1"]
                              1)  #_"Highcharts.getOptions().colors[1]"
              :color (nth ["#2caffe", "#544fc5", "#00e272", "#fe6a35", "#6b8abc", "#d568fb", "#2ee0ca", "#fa4b42", "#feb56a", "#91e8e1"]
                          2) #_"Highcharts.getOptions().colors[2]"
              :fillOpacity 0.5,
              :name "Elevation",
              :marker {:enabled false},
              :threshold nil}]}))

;; TODO: Line chart with 500k points
;;       https://www.highcharts.com/demo/highcharts/line-boost

(defn time-series-zoomable
  []
  (json/generate-string
   (let [data (cheshire.core/parse-string (:out (sh/sh "curl" "https://cdn.jsdelivr.net/gh/highcharts/highcharts@v10.3.3/samples/data/usdeur.json")))]
     {:chart {:zoomType "x"},
      :title {:text "USD to EUR exchange rate over time",
              :align "left"},
      :subtitle {:text "TODO" #_"document.ontouchstart === undefined ?"
                 #_"Click and drag in the plot area to zoom in" #_":" #_"Pinch the chart to zoom in",
                 :align "left"},
      :xAxis {:type "datetime"},
      :yAxis {:title {:text "Exchange rate"}}

      :legend {:enabled false},
      ;; TODO: Fix plotOptions
      :plotOptions {} #_{:area {:fillColor {:linearGradient {:x1​ 0,
                                                             :y1​ 0,
                                                             :x2​ 0,
                                                             :y2​ 1},
                                            :stops [[0, (get colors 0)],
                                                    [1, (get colors 1) #_".setOpacity(0).get(\"rgba\")"]]}

                                :marker {:radius 2},
                                :lineWidth 1,
                                :states {:hover {:lineWidth 1}}

                                :threshold nil}}

      :series [{:type "area",
                :name "USD to EUR",
                :data data}]})))

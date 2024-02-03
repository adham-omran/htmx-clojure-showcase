(ns core
  (:require [scicloj.kindly.v4.kind :as kind]))

;; # Hello

(defn vega-lite-point-plot [data]
  (-> {:data {:values data},
       :mark "point"
       :encoding
       {:size {:field "w" :type "quantitative"}
        :x {:field "x", :type "quantitative"},
        :y {:field "y", :type "quantitative"},
        :fill {:field "z", :type "nominal"}}}
      kind/vega-lite))

(defn random-data [n]
  (->> (repeatedly n #(- (rand) 0.5))
       (reductions +)
       (map-indexed (fn [x y]
                      {:w (rand-int 9)
                       :z (rand-int 9)
                       :x x
                       :y y}))))

(defn random-vega-lite-plot [n]
  (-> n
      random-data
      vega-lite-point-plot))

(comment
  (random-vega-lite-plot 9))

(random-vega-lite-plot 9)

;; # Another Way

(-> {:data {:values "x,y
1,1
2,4
3,9
-1,1
-2,4
-3,9"
            :format {:type :csv}},
     :mark "point"
     :encoding
     {:x {:field "x", :type "quantitative"}
      :y {:field "y", :type "quantitative"}}}
    kind/vega-lite)

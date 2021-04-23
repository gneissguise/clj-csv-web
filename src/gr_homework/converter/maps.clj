(ns gr-homework.converter.maps
  (:require [clojure.string :as s]))

(defn matr->map
  "Converts 2d matrix into a map using the elements in the first row as keys"
  [v]
  (let [ks (map keyword (first v))
        body (rest v)]
    (map #(zipmap ks %) body)))

(defn matrim
  "Trims strings nested in a 2d matrix"
  [v]
  (mapv #(mapv s/trim %) v))
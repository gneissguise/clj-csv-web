(ns gr-homework.converter.maps
  (:require [clojure.string :as s]))

(defn matrix-to-map
  [v]
  (let [ks (map keyword (first v))
        body (rest v)]
    (map #(zipmap ks %) body)))

(defn matrix-trim-spaces
  [v]
  (mapv #(mapv s/trim %) v))
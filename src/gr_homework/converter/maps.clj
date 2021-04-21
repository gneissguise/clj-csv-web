(ns gr-homework.converter.maps)

(defn matrix-to-map
  [v]
  (let [ks (map keyword (first v))
        body (rest v)]
    (map #(zipmap ks %) body)))
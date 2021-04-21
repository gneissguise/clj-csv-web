(ns gr-homework.sort-handler)

;; Helper functions
(defn get-index
  "Wraps get-index, returns index of v header, using s term, and falling back to value fv or 0"
  ([s v]
   (get-index s v 0))
  ([s v fv]
   (or (.indexOf (first v) s) fv)))

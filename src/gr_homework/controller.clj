(ns gr-homework.controller
  (:require [gr-homework.converter.maps :as cm]
            [gr-homework.handler.file :as fh]
            [gr-homework.handler.print :as ph]
            [gr-homework.handler.sort :as sh]))
(defn merge-files-to-vec
  [files]
  (loop [f files
         header []
         body []]
    (if (empty? f)
      (into header body) ;; empty condition: Return new vector with head & body concatened
      (let [f1 (first f) ;; not empty: Take the next file, process out the header and merge body into previous
            v  (vec (fh/read-file f1))
            h (if (empty? header)
                [(first v)]
                header)
            sv (subvec v 1)
            b (if (empty? body)
                sv
                (into body sv))]
        (recur (rest f) h b)))))

(defn process-to-map
  [f]
  (->> (merge-files-to-vec f)
       (cm/matrix-trim-spaces)
       (cm/matrix-to-map)))

(defn display
  [files srt]
  (let [tbl (process-to-map files)
        get-cmpr (if (= srt :Demo)
               [(sh/lookup-sort :Gender)
                (sh/lookup-sort :DateOfBirth)
                (sh/lookup-sort :LastName)]
               [(sh/lookup-sort srt)])]
    (doseq [c get-cmpr]
      (let [heading (first c)
            compr (last c)]
        (ph/print-heading heading)
        (ph/print-table tbl compr)))))


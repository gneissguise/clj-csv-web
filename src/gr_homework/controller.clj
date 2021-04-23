(ns gr-homework.controller
  (:require [gr-homework.converter.maps :as cm]
            [gr-homework.handler.file :as fh]
            [gr-homework.handler.print :as ph]
            [gr-homework.handler.sort :as sh]))

;; I am sure there's a way to simplify this down to 2-3 lines,,
;; Though my brain is stuck thinking procedurally at the moment
(defn merge-file
  "Merges files to one matrixed set, sharing the header row"
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

(defn process-file
  "Takes a vec of filenames, merges into a 2d matrix, trims spaces from
   fields, then converts to a map"
  [f]
  (-> f
      merge-file
      cm/matrim
      cm/matr->map))

(defn display
  "Display endpoint, takes options passed to the command line
   and prints a table with the data formatted into a table and sorted"
  [files srt]
  (let [tbl (process-file files)
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

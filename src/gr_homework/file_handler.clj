(ns gr-homework.file-handler
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn read-file
  "Reads the filename passed as argument f
   Returns vectors containing csv cols and rows"
  [f]
  (with-open [file (io/reader f)]
    (doall
     (csv/read-csv file))))

(defn write-file
  "Formats vector v to csv and writes to file f"
  [f v]
  (with-open [file (io/writer f)]
    (csv/write-csv file v)))
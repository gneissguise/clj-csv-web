(ns gr-homework.file-handler
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

;; Delimiter that we fall back to
;; NOTE: clojure.data.csv will automatically default to a comma
;; if no  delimiter is provided to read-csv or write-csv.  I am using
;; defaults here for visibility's sake.
(def default-file-delim ",")

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

(defn detect-delim
  "Scan a string and look for a possible candidate out of comma ',' pipe '|', or space ' ' and return first match"
  [s]
  (let [comma? #(re-find #"," %)
        pipe? #(re-find #"\|" %)
        space? #(re-find #" " %)
        default default-file-delim]
    (cond
      (comma? s) ","
      (pipe? s) "|"
      (space? s) " "
      :else default)))

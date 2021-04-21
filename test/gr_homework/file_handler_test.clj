(ns gr-homework.file-handler-test
  (:require [clojure.test
             :refer [deftest
                     testing
                     is
                     function?
                     use-fixtures
                     are]]
            [clojure.string :as string]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [gr-homework.file-handler :as gr]))

;; Global declarations and helper functions
(def test-file "csv.test")
(def another-file "another-file.test")
(defn exists?
  "check if file exists"
  [f]
  (.exists (io/file f)))

(defn delete-file
  "deletes file"
  [f]
  (io/delete-file f))

(defn del-when-exists
  "fn composed of a when predicate to check if files exists, then deletes them"
  [& files]
  (doseq [f files]
    (when (exists? f)
      (delete-file f))))

;; Fixtures
(defn file-fixture
  "Fixture for csv file setup"
  [test-fn]
  ;; Generate files for csv-file-processing
  (del-when-exists test-file another-file)
  (spit test-file "wombo,combo\nfoo,bar")

  ;; Run test fn
  (test-fn)

  ;; tear-down
  (del-when-exists test-file another-file))

;; Register fixtures
(use-fixtures :once file-fixture)

;; Tests
(deftest dependency-check
  ;; Simple dependency sanity check
  (testing "clojure.data.csv"
    (is (function? csv/read-csv)))
  (testing "clojure.java.io"
    (is (function? io/reader))))

(deftest csv-file-processing
  (testing "read-file reads from a file"
    (is (= (gr/read-file test-file) [["wombo" "combo"]
                                     ["foo" "bar"]])))
  (testing "write-file writes to a file"
    (is (= (do
             (gr/write-file another-file
                            [["name" "age" "hobby"]
                             ["frodo" "50" "lazing around the shire"]
                             ["samwise" "38" "protecting master frodo"]
                             ["pippin" "27" "throwing stones into pits in abandoned mines"]
                             ["merry" "36" "riding ents"]])
             (slurp another-file))
           (str "name,age,hobby\n"
                "frodo,50,lazing around the shire\n"
                "samwise,38,protecting master frodo\n"
                "pippin,27,throwing stones into pits in abandoned mines\n"
                "merry,36,riding ents\n")))))

(deftest file-delim-detections
  (are [exp d s] (exp (gr/detect-delim s) d)
    = "," "1,2,3,4,5,6,7,8,9,0"
    = "|" "this|is|how|we|do|it"
    = " " "I mean this is pretty much just a sentence."
    = gr/default-file-delim (string/join
                             gr/default-file-delim
                             ["el" "gallo" "es" "muy" "guapo"])
    not= "" (string/join
             gr/default-file-delim
             ["are" "we" "really" "testing" "" "values?"])
    not= nil (string/join
              gr/default-file-delim
              ["are" "we" "really" "testing" nil "values?"])))

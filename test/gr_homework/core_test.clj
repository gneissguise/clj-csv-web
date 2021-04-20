(ns gr-homework.core-test
  (:require [clojure.test :refer [deftest testing is function?]]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [gr-homework.core :as gr]))

(deftest main-func
  (testing "-main prints: Hello, World!"
    (is (= "Hello, World!\n" (with-out-str (gr/-main))))))

(deftest dependency-check
  ;; Simple dependency sanity check
  (testing "clojure.data.csv"
    (is (function? csv/read-csv)))
  (testing "clojure.java.i(o"
    (is (function? io/reader))))
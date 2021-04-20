(ns gr-homework.core-test
  (:require [clojure.test
             :refer [deftest
                     testing
                     is]]
            [gr-homework.core :as gr]))

;; Tests
(deftest main-func
  (testing "-main prints: Hello, World!"
    (is (= "Hello, World!\n" (with-out-str (gr/-main))))))

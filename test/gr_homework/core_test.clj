(ns gr-homework.core-test
  (:require [clojure.test
             :refer [deftest
                     testing
                     is
                     function?]]
            [gr-homework.core :as gr]))

;; Tests
(deftest main-func
  ;; Simple dependency sanity check
  (testing "exit"
    (is (function? gr/exit)))
  (testing "start-up"
    (is (function? gr/start-up)))
  (testing "-main"
    (is (function? gr/-main))))
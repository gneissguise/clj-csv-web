(ns rest-api.handler-test
  (:require [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [rest-api.handler :as h]))

(deftest test-app
  (testing "main route"
    (let [response (h/reloadable-app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Go time!"))))

  (testing "not-found route"
    (let [response (h/reloadable-app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))
  (testing "keywordize helper"
    (is (= (h/keywordize {"Super" "Duper" "Extra" "Special"})
           {:Super "Duper", :Extra "Special"}))))

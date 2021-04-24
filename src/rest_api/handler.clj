(ns rest-api.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.reload :refer [wrap-reload]]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [ring.middleware.json :refer [wrap-json-response
                                          wrap-json-body]]
            [rest-api.endpoints.records :as records]))

(defn keywordize [m]
  (zipmap (map keyword (keys m)) (vals m)))

;; Basic route setup
(defroutes app-routes
  (GET "/" [] "Go time!")
  (GET  "/records" [] (records/get! "lastname"))
  (GET "/records/:srt" [srt] (records/get! srt))
  (POST "/records" {body :body} 
    (records/post! (keywordize body)))
  (route/not-found "Not Found"))

;; Update CORS rule to accept everything
(wrap-cors app-routes #".*")
(wrap-cors app-routes identity)

;; middleware wrapper
(def app
  (-> app-routes
      (wrap-cors #".*")
      (wrap-cors identity)
      wrap-json-body
      wrap-json-response))

(def reloadable-app
  (wrap-reload #'app))
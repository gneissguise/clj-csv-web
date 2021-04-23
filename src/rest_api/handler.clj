(ns rest-api.handler
  (:require [compojure.core :refer [defroutes GET POST context]]
            [compojure.route :as route]
            [ring.middleware.reload :refer [wrap-reload]]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [ring.middleware.json :refer [wrap-json-response
                                          wrap-json-body]]
            [rest-api.endpoints.records :as records]))

;; Basic route setup
(defroutes app-routes
  (GET "/" [] "Go time!")
  (context "/records" []
    (defroutes records-routes
      (GET  "/" [] (records/get!))
      (POST "/" {body :body} (records/post! body))
      (context "/:id" [id]
        (defroutes record-routes
          (GET    "/" [] (records/get! id))))))
  (route/not-found "Not Found"))

;; (defroutes app-routes
;;   (GET "/" [] 
;;     "Locked and loaded, ready to roll.")
;;   (GET "/records/:srt" [srt]
;;     (records/get! srt))
;;   (POST "/records" [args]
;;     (records/post! args)) 
;;   (route/not-found 
;;    "404040404 File Not Found Not File"))

;; accept everything
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
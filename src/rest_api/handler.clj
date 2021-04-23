(ns rest-api.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults
                                              site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [gr-homework.handler.sort :as sh]))


(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/records/:srt" [srt]
    (str "recorts sorted by " (sh/sort-keymap srt)))
  (POST "/records" [args]
    (str "Cool, " args)) 
  (route/not-found "Not Found"))

;; accept everything
(wrap-cors app-routes #".*")
(wrap-cors app-routes identity)

(def app
  (-> app-routes
      
      (wrap-cors #".*")
      (wrap-cors identity)))

(def reloadable-app
  (wrap-reload #'app))
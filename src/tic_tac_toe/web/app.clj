(ns tic-tac-toe.web.app
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [not-found,file-response]]
            [tic-tac-toe.web.runner :as web-runner]))

(def routes 
  {
   "/" (fn [request] (file-response "index.html" {:root "resources/public"}))
   "/move" web-runner/move
   "/new-game" web-runner/new-game 
   })

(defn route [request]
  (let [handler (get routes (:uri request))]
      (if (nil? handler)
        (not-found "Resource not found")
        (handler request))))

(def app 
  (-> route 
      (wrap-resource "public")
      (wrap-params)))

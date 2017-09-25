(ns tic-tac-toe.web.app
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [response,not-found,file-response]]
            [clojure.data.json :as json]
            [tic-tac-toe.web.runner :as web-runner]))

(defn wrap-json [handler]
  (fn [request]
    (-> (handler request)
        (json/write-str)
        (response))))

(def routes 
  {
   "/" (fn [request] (file-response "index.html" {:root "resources/public"}))
   "/move" (wrap-json web-runner/move)
   "/new-game" (wrap-json web-runner/new-game) 
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

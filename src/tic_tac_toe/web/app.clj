(ns tic-tac-toe.web.app
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [response,not-found,file-response]]
            [ring.middleware.json :refer [wrap-json-response,wrap-json-body]]
            [tic-tac-toe.web.handler :as web-handler]))


(defn index [request]
  (file-response "index.html" {:root "resources/public"}))

(def routes 
  {:get   {"/" index}
   :post  {"/move" web-handler/handle
           "/new-game" web-handler/handle-new}})

(defn route [request]
  (let [handler (get ((:request-method request) routes) (:uri request))]
      (if (nil? handler)
        (not-found "Resource not found")
        (handler request))))

(def app 
  (-> route 
      (wrap-resource "public")
      (wrap-params)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))

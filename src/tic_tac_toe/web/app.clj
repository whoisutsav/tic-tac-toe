(ns tic-tac-toe.web.app
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.response :refer [not-found]]
            [ring.middleware.json :refer [wrap-json-response,wrap-json-body]]
            [tic-tac-toe.web.handlers :as handlers]))


(def routes 
  {:get   {"/" handlers/index}
   :post  {"/game" handlers/new-game}
   :put {"/game" handlers/update-game}})

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

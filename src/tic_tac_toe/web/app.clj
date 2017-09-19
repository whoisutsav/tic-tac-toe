(ns tic-tac-toe.web.app
  (:require [ring.middleware.params :refer [wrap-params]]
            [tic-tac-toe.web.web-processor :refer [wrap-request,wrap-response]]
            [tic-tac-toe.web.web-runner :as web-runner]))

(def app 
  (-> web-runner/handle 
      (wrap-request)
      (wrap-params)
      (wrap-response)))

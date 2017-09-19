(ns tic-tac-toe.web.processor
  (:require [tic-tac-toe.web.web-runner :as web-runner]
            [ring.util.response :refer [response]]
            [clojure.data.json :as json]))


(defn map-request [request]
  (let [request-params (:form-params request)]
      {:board (read-string (get request-params "board"))
       :current-player (read-string (get request-params "current-player"))
       :opponent-player (read-string (get request-params "opponent-player"))
       :move (read-string (get request-params "move"))}))

(defn wrap-request [handler]
  (fn [request] 
    (-> (map-request request)
        (handler))))

(defn map-response [response]
  (json/write-str response))

(defn wrap-response [handler]
  (fn [request]
    (-> (handler request)
        (map-response)
        (response))))

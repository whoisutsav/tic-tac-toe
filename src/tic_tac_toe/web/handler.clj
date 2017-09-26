(ns tic-tac-toe.web.handler
  (:require [clojure.walk :as walk]
            [tic-tac-toe.web.runner :as runner]
            [ring.util.response :refer [response]]))

(defn- convert [params]
  (walk/postwalk #(if (string? %) (keyword %) %) params))

(defn handle [request]
  (let [game (convert (:body request))]
    (-> (runner/update-game game) (response))))

(defn handle-new [request]
  (let [opponent-type (-> (convert (:params request)) (:opponent :computer))]
    (-> (runner/new-game opponent-type) (response))))


(ns tic-tac-toe.web.handlers
  (:require [clojure.walk :as walk]
            [tic-tac-toe.web.game-runner :as game-runner]
            [ring.util.response :refer [response,file-response]]))

(defn- keywordize [params]
  (walk/postwalk #(if (string? %) (keyword %) %) params))

(defn index [request]
  (file-response "index.html" {:root "resources/public"}))

(defn update-game [request]
  (let [game (keywordize (:body request))]
    (-> (game-runner/move game) (response))))

(defn new-game [request]
  (let [opponent-type (-> (:params request) (get "opponent" "keyword") (keyword))
        size (-> (:params request) (get "size" "3") (read-string))]
    (-> (game-runner/init opponent-type size) (response))))


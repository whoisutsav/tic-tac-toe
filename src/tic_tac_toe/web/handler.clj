(ns tic-tac-toe.web.handler
  (:require [tic-tac-toe.board :as board]
            [clojure.walk :as walk]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.basic-game :as basic-game]
            [tic-tac-toe.player.human-web] ;TODO remove
            [tic-tac-toe.player.hard-computer] ;TODO remove
            [tic-tac-toe.console.player-setup :as player]
            [ring.util.response :refer [response]]))

(def active "active")
(def win "win")
(def draw "draw")

(defn convert [params]
  (walk/postwalk #(if (string? %) (keyword %) %) params))

(defn add-meta [params winner state]
  (-> (assoc params :winner winner)
      (assoc :state state)))

(defn- default-meta [params]
  (add-meta params nil active))

(defn- winner-meta [params winner]
  (add-meta params winner win)) 

(defn- draw-meta [params]
  (add-meta params nil draw))

(defn- new-game [opponent-type]
  (let [current-player (player/make-player :human-web :X)
        opponent-player (player/make-player opponent-type :O)
        players [current-player opponent-player]
        board (board/new-board 3)] 
    (game/make-game players board)))

(defn handle-new [request]
  (let [opponent-type (-> (convert (:params request)) (:opponent :computer))]
    (-> (new-game opponent-type) (default-meta) (response))))

(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (winner-meta game winner)
    (draw-meta game)))

(declare move)

(defn- recur-move [game]
  (let [player-type (:type (:current-player game))] 
    (if (or (= :computer player-type) (= :hard-computer player-type))
      (move game)
      (default-meta game))))

(defn- move [game]
  (let [updated-game (basic-game/take-turn game)]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (recur-move updated-game))))

(defn handle [request]
  (let [game (convert (:body request))]
    (-> game (move) (response))))


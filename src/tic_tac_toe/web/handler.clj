(ns tic-tac-toe.web.handler
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.basic-game :as basic-game]
            [tic-tac-toe.player.human-web] ;TODO remove
            [tic-tac-toe.player.hard-computer] ;TODO remove
            [tic-tac-toe.console.player-setup :as player]
            [ring.util.response :refer [response]]))


(defn convert-to-game [request]
  (let [params (-> (:form-params request))
        board (->> (get params "board")
                   (mapv keyword))
        current-player (->> (get params "marker")
                            (keyword)
                            (player/make-player :human-web))
        opponent-player (-> (get params "opponent")
                            (keyword)
                            (player/make-player :O))
        move (-> (get params "move") (read-string))]
    {:board board
     :current-player current-player
     :opponent-player opponent-player
     :move move
     }))


(def IN_PROGRESS "IN_PROGRESS")
(def WIN "WIN")
(def DRAW "DRAW")

(defn ttt-response [params] 
  (-> { :status (:status params IN_PROGRESS)
        :board (:board params) 
        :marker (-> params :current-player :marker)
        :opponent (-> params :opponent-player :type)
        :winner (:winner params)}
      response)) 

(defn- create-new-game [opponent-type]
  (let [current-player (player/make-player :human-web :X)
        opponent-player (player/make-player opponent-type :O)
        players [current-player opponent-player]
        board (board/new-board 3)] 
    (game/make-game players board)))

(defn handle-new [request]
  (let [opponent-type (-> (:params request)
                          (get "opponent" :computer)
                          keyword)]
    (-> (create-new-game opponent-type) (ttt-response))))

(defn- flag-winner [params winner]
  (-> (assoc params :winner winner)
      (assoc :status WIN))) 

(defn- flag-draw [params]
  (assoc params :status DRAW))

(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (-> game (flag-winner winner))
    (-> game (flag-draw))))

(declare move)

(defn- recur-move [game]
  (let [player-type (-> (:current-player game) 
                        :type)] 
    (if (or (= :computer player-type) (= :hard-computer player-type))
      (move game)
      game)))

(defn- move [game]
  (let [updated-game (basic-game/take-turn game)]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (recur-move updated-game))))

(defn handle [request]
  (let [game (convert-to-game request)]
    (-> game (move) (ttt-response))))


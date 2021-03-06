package com.mlx.customrxjava.core

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */

/**
 * Project:CustomRxJava
 * Created by MLX on 2020/10/7.
 */
class MlxSubscribeObservable <T>(val source:MlxObservableOnSubscribe<T>, private val thread:Int):MlxObservableOnSubscribe<T>{

    override fun subscribe(emitter: MlxObserver<T>){
        val downStream=MlxSubscribeObserver(emitter)
        Schedulers.INSTANCE.submitSubscribeWork(source,downStream,thread)
    }

    class MlxSubscribeObserver<T>(val emitter:MlxObserver<T>):MlxObserver<T>{

        override fun onSubscribe() {
            emitter.onSubscribe()
        }

        override fun onNext(item: T) {
            emitter.onNext(item)
        }

        override fun onError(e: Throwable) {
            emitter.onError(e)
        }

        override fun onComplete() {
            emitter.onComplete()
        }

    }

}
package com.alkhalildevelopers.apps.quranepak.presenter;

public interface Presenter<T> {
  void bind(T what);
  void unbind(T what);
}

package com.github.ginirohikocha.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ginirohikocha.spring.controller.exception.UnregisteredListenerException;
import com.github.ginirohikocha.spring.vo.Json;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/22 23:18
 */
public abstract class BaseController<Entity> {

    protected IService<Entity> service;

    @Setter
    private OnQueryListener<Entity> onQueryListener;
    @Setter
    private OnCreateListener<Entity> onCreateListener;
    @Setter
    private OnUpdateListener<Entity> onUpdateListener;
    @Setter
    private OnDeleteListener<Entity> onDeleteListener;

    public BaseController(IService<Entity> service) {
        this.service = service;
    }

    @GetMapping
    public Object query(HttpServletRequest request) {
        try {
            if (onQueryListener == null) {
                throw new UnregisteredListenerException("Query");
            }
            QueryWrapper<Entity> queryWrapper =  onQueryListener.onWrapper(request.getParameterMap(), new QueryWrapper<>());

            List<Entity> entities = service.list(queryWrapper);
            List<Entity> entities_ = onQueryListener.onList(entities);

            Json json = Json
                    .succ()
                    .data(entities_ == null?entities:entities_);
            Json rtn = onQueryListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return responseUnregisteredListener();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onQueryListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    @GetMapping("{id}")
    public Object query(HttpServletRequest request, @PathVariable(value = "id") String[] id) {
        try {
            if (onQueryListener == null) {
                throw new UnregisteredListenerException("Query");
            }
            QueryWrapper<Entity> queryWrapper = new QueryWrapper<Entity>().in("id", Arrays.asList(id));
            QueryWrapper<Entity> queryWrapper_ = onQueryListener.onWrapper(request.getParameterMap(), queryWrapper);

            List<Entity> entities = service.list(queryWrapper_ == null?queryWrapper:queryWrapper_);
            List<Entity> entities_ = onQueryListener.onList(entities);

            Json json = Json
                    .succ()
                    .data(entities_ == null?entities:entities_);
            Json rtn = onQueryListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return responseUnregisteredListener();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onQueryListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    @PostMapping
    @SuppressWarnings("unchecked")
    public Object create(@RequestBody String requestBody) {
        try {
            if (onCreateListener == null) {
                throw new UnregisteredListenerException("Create");
            }
            String requestBody_ = onCreateListener.onRequest(requestBody);
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Entity entity = JSON.toJavaObject(
                    JSON.parseObject(requestBody_ == null?requestBody:requestBody_), (Class<Entity>) type.getActualTypeArguments()[0]);
            Entity entity_ = onCreateListener.onToEntity(entity);

            service.save(entity_ == null?entity:entity_);

            Json json = Json.succ();
            Json rtn = onCreateListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return responseUnregisteredListener();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onCreateListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    @PutMapping
    @SuppressWarnings("unchecked")
    public Object update(@RequestBody String requestBody) {
        try {
            if (onUpdateListener == null) {
                throw new UnregisteredListenerException("Update");
            }
            String requestBody_ = onUpdateListener.onRequest(requestBody);

            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Object object = JSON.parse(requestBody_ == null?requestBody:requestBody_);
            if (object instanceof JSONObject) {
                Entity entity = JSON.toJavaObject((JSONObject) object, (Class<Entity>) type.getActualTypeArguments()[0]);
                Entity entity_ = onUpdateListener.onToEntity(entity);

                service.updateById(entity_ == null?entity:entity_);
            } else if (object instanceof JSONArray) {
                List<Entity> entities = ((JSONArray) object).toJavaList((Class<Entity>) type.getActualTypeArguments()[0]);
                List<Entity> entities_ = onUpdateListener.onToEntities(entities);

                service.updateBatchById(entities_ == null?entities:entities_);
            }

            Json json = Json.succ();
            Json rtn = onUpdateListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return Json.fail();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onUpdateListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    @PatchMapping("{id}")
    public Object update(HttpServletRequest request, @PathVariable(value = "id") String id) {
        try {
            if (onUpdateListener == null) {
                throw new UnregisteredListenerException("Update");
            }
            Entity entity = service.getById(id);
            Entity entity_ = onUpdateListener.onQueryEntity(request.getParameterMap(), entity);

            service.updateById(entity_ == null?entity:entity_);

            Json json = Json.succ();
            Json rtn = onUpdateListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return responseUnregisteredListener();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onUpdateListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(value = "id") String[] id) {
        try {
            if (onDeleteListener == null) {
                throw new UnregisteredListenerException("Delete");
            }
            service.removeByIds(Arrays.asList(id));

            Json json = Json
                    .succ();
            Json rtn = onDeleteListener.onReturn(json);
            return rtn == null?json:rtn;
        } catch (UnregisteredListenerException unregisteredListenerException) {
            return responseUnregisteredListener();
        } catch (Exception exception) {
            exception.printStackTrace();
            Json json = Json.fail();
            Json rtn = onDeleteListener.onError(exception, json);
            return rtn == null?json:rtn;
        }
    }

    /**
     * 当访问未注册监听器的路由时调用，进行处理
     *
     * @return 响应返回
     */
    public abstract Object responseUnregisteredListener();

    /**
     * 注册该监听器以启用query路由，返回null默认表示不进行处理
     *
     * @param <Entity> 与Controller和ISERVICE对应的实体类
     */
    protected interface OnQueryListener<Entity> {
        /**
         * 当构建查询条件时调用，用于根据params来处理wrapper查询条件
         *
         * @param params 请求参数，由HttpServletRequest的getParameterMap()方法传入
         * @param queryWrapper 查询wrapper，由QueryWrapper构造方法传入，当访问{id}路由时默认带有id查询条件
         * @return 处理结果，用于service查询
         */
        QueryWrapper<Entity> onWrapper(Map<String, String[]> params, QueryWrapper<Entity> queryWrapper);

        /**
         * 当获得查询结果时调用，用于对查询结果进行处理
         *
         * @param entities 查询结果，由IService的list方法传入
         * @return 处理结果，用于加入Json后返回
         */
        List<Entity> onList(List<Entity> entities);

        /**
         * 当准备返回结果时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onReturn(Json json);

        /**
         * 当发生异常错误时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onError(Exception exception, Json json);
    }

    /**
     * 注册该监听器以启用create路由，返回null默认表示不进行处理
     *
     * @param <Entity> 与Controller和ISERVICE对应的实体类
     */
    protected interface OnCreateListener<Entity> {

        /**
         * 当收到请求时调用，POST请求Body，内容为对应实体的全量Json字符串
         *
         * @param requestBody JSON的toJavaObject方法转换的实体
         * @return Json字符串
         */
        String onRequest(String requestBody);

        /**
         * 当转换成实体时调用，用于对生成的实体进行处理
         *
         * @param entity JSON的toJavaObject方法转换的实体
         * @return 实体
         */
        Entity onToEntity(Entity entity);

        /**
         * 当准备返回结果时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onReturn(Json json);

        /**
         * 当发生异常错误时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onError(Exception exception, Json json);
    }

    /**
     * 注册该监听器以启用update路由，返回null默认表示不进行处理
     *
     * @param <Entity> 与Controller和ISERVICE对应的实体类
     */
    protected interface OnUpdateListener<Entity> {

        /**
         * 仅在Post请求时调用
         * 当收到请求时调用，PUT请求Body，内容为对应实体的全量Json字符串
         *
         * @param requestBody JSON的toJavaObject方法转换的实体
         * @return Json字符串
         */
        String onRequest(String requestBody);

        /**
         * 仅在Post请求时调用
         * 当转换成实体时调用，用于对生成的实体进行处理
         *
         * @param entity JSON的toJavaObject方法转换的实体
         * @return 实体
         */
        Entity onToEntity(Entity entity);

        /**
         * 仅在Post请求时调用
         * 当转换成实体时调用，用于对生成的实体list进行处理
         *
         * @param entities JSONArray的toJavaList方法转换的实体list
         * @return 实体list
         */
        List<Entity> onToEntities(List<Entity> entities);

        /**
         * 仅在Put请求时调用
         * 当转换成实体时调用，用于对生成的实体进行处理，通过传入的params来对entity具体属性进行更新
         *
         * @param params 请求参数，由HttpServletRequest的getParameterMap()方法传入
         * @param entity service查询获得的实体
         * @return 实体
         */
        Entity onQueryEntity(Map<String, String[]> params, Entity entity);

        /**
         * 当准备返回结果时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onReturn(Json json);

        /**
         * 当发生异常错误时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onError(Exception exception, Json json);

    }

    /**
     * 注册该监听器以启用delete路由，返回null默认表示不进行处理
     *
     * @param <Entity> 与Controller和ISERVICE对应的实体类
     */
    protected interface OnDeleteListener<Entity> {

        /**
         * 当准备返回结果时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onReturn(Json json);

        /**
         * 当发生异常错误时调用，用于对返回Json进行处理
         *
         * @param json 由Json生成返回HashMap
         * @return 响应返回Json
         */
        Json onError(Exception exception, Json json);
    }
}

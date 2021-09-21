---
title: COMP218
layout: article
aside:
 toc: true
sidebar:
 nav: comp218
---
{% for post in site.posts %}
{% if post.tags contains "COMP218" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}

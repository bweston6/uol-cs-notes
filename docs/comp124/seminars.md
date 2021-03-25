---
title: COMP124 - Seminars
layout: article
aside:
 toc: true
sidebar:
 nav: comp124
---
{% for post in site.posts %}
{% if post.tags contains "Seminars" and post.tags contains "COMP124" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}

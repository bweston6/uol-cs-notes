---
title: COMP202 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp202
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.tags contains "COMP202" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
